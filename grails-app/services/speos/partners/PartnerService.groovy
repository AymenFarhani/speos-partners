package speos.partners

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
class PartnerService {

    def getPartners() {
        def partners = Partner.list()
        return ([partners: partners]) as JSON
    }

    def getPartnerById(Long id) {
        Partner.findById(id)
    }

    def createPartner(Partner partner) {
        if(!partner.validate()){
            throw new ValidationException("Invalid partner data", partner.errors)
        }
        partner.save(flush: true)
        return partner
    }

    def updatePartner(Long id, Partner updatedPartner) {
        Partner partner = getPartnerById(id)
        partner.properties = updatedPartner.properties
        if (!partner.validate()) {
            throw new ValidationException("Invalid partner data", partner.errors)
        }
        partner.save(flush: true)
        return partner
    }

    def deletePartnerById(Long id) {
        Partner partner = getPartnerById(id)
        partner.delete(flush: true)
    }

}
