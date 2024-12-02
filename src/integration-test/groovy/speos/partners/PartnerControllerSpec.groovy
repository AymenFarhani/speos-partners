package speos.partners

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import grails.testing.web.controllers.ControllerUnitTest
import org.openqa.selenium.NotFoundException
import spock.lang.Specification

@Integration
@Rollback
class PartnerControllerSpec extends Specification implements ControllerUnitTest<PartnerController> {

    PartnerService partnerService

    def setup() {
        partnerService = Mock(PartnerService)
        controller.partnerService = partnerService
    }

    void "test getPartners returns a list of partners"() {
        given:
        def partners = [new Partner(name: "Test Company", reference: "TC123", locale: Locale.ENGLISH, expirationTime: new Date()),
                        new Partner(name: "Test Company2", reference: "TC124", locale: Locale.ENGLISH, expirationTime: new Date())]
        partnerService.getPartners(10,0) >> partners

        when:
        controller.getPartners(10,0)

        then:
        response.status == 200
    }

    void "test getPartnerById returns a partner when found"() {
        given:
        Long partnerId = 1L
        Partner partner = new Partner(name: "Test Company", reference: "TC123", locale: Locale.ENGLISH, expirationTime: new Date())
        partnerService.getPartnerById(partnerId) >> partner

        when:
        controller.getPartnerById(partnerId)

        then:
        response.status == 200
        response.json.reference == partner.reference
        response.json.name == partner.name
        response.json.locale == partner.locale.toString()
    }

    void "test getPartnerById returns NOT_FOUND when partner is not found"() {
        given:
        Long partnerId = 1L
        partnerService.getPartnerById(partnerId) >> { throw new NotFoundException("Partner not found") }

        when:
        controller.getPartnerById(partnerId)

        then:
        response.status == 404
        response.json.code == 404
        response.json.message == "Partner with id: $partnerId not found."
    }

    void "test createPartner responds 201 when created successfully"() {
        given:
        Partner newPartner = new Partner(name: "Test Company", reference: " ", locale: Locale.ENGLISH, expirationTime: new Date())
        partnerService.createPartner(newPartner) >> true

        when:
        controller.createPartner(newPartner)

        then:
        response.status == 201
    }

    void "test createPartner responds 400 when creation fails"() {
        given:
        Partner newPartner = new Partner(name: "Test Company", reference: "TC123", locale: Locale.ENGLISH, expirationTime: new Date())
        partnerService.createPartner(newPartner) >> false

        when:
        controller.createPartner(newPartner)

        then:
        response.status == 400
        response.json.code == 400
        response.json.message == "Bad Request Error."
    }

    void "test updatePartner responds 204 when updated successfully"() {
        given:
        Long partnerId = 1L
        Partner updatedPartner = new Partner(name: "Test Company", reference: "TC123", locale: Locale.ENGLISH, expirationTime: new Date())
        partnerService.updatePartner(partnerId, updatedPartner) >> true

        when:
        controller.updatePartner(partnerId, updatedPartner)

        then:
        response.status == 204
    }

    void "test deletePartnerById responds 204 when deleted successfully"() {
        given:
        Long partnerId = 1L
        partnerService.deletePartnerById(partnerId) >> true

        when:
        controller.deletePartnerById(partnerId)

        then:
        response.status == 204
    }

}