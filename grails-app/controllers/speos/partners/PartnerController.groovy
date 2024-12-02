package speos.partners

import grails.converters.JSON
import grails.rest.RestfulController
import javassist.NotFoundException
import org.springframework.http.HttpStatus

class PartnerController extends RestfulController<Partner> {

    PartnerService partnerService

    PartnerController() {
        super(Partner)
    }

    def getPartners() {
        int max = params.int('max') ?: 5
        int offset = params.int('offset') ?: 0

        // Fetch paginated results
        def partners = partnerService.getPartners(params)
        def totalPartnersCount = Partner.count()

        // Create a JSON response
         def response = [partners: partners, total: totalPartnersCount, max: max, offset: offset]
        render (response as JSON)
    }

    def getPartnerById(Long id) {
        try {
            Partner partner = partnerService.getPartnerById(id)
            render partner as JSON
        } catch (Exception exception) {
            displayNotFoundError(id)
        }
    }

    def createPartner(Partner partner) {
        if (partnerService.createPartner(partner)) {
            render status: 201
        } else {
            displayBadRequestError()
        }
    }

    def updatePartner(Long id, Partner partner) {
        try {
            partnerService.updatePartner(id, partner)
            render status: 204
        } catch (NotFoundException exception) {
            displayNotFoundError(id)
        }
        catch (Exception exception) {
            displayInternalServerError()
        }
    }

    def deletePartnerById(Long id) {
        try {
            partnerService.deletePartnerById(id)
            render status: 204
        } catch (NotFoundException e) {
            displayNotFoundError(id)
        }
        catch (Exception exception) {
            displayInternalServerError()
        }
    }

    def displayNotFoundError(Long id) {
        render status: HttpStatus.NOT_FOUND, contentType: 'application/json', text:
                """{
                    "code": 404,
                    "message": "Partner with id: $id not found."
                }"""
    }

    def displayBadRequestError() {
        render status: HttpStatus.BAD_REQUEST, contentType: 'application/json', text:
                """{
                    "code": 400,
                    "message": "Bad Request Error."
                }"""
    }

    def displayInternalServerError() {
        render status: HttpStatus.INTERNAL_SERVER_ERROR, contentType: 'application/json', text:
                """{
                "code": 500,
                "message": "Internal Server Error."
            }"""
    }
}

