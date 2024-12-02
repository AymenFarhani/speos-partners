package speos.partners

import grails.test.hibernate.HibernateSpec

class PartnerSpec extends HibernateSpec{

    void 'test domain class with invalid data'() {
        when:
        Partner partner = new Partner(name: "Test Company", reference: " ", locale: Locale.ENGLISH, expirationTime: new Date())
        partner.save()

        then:
        partner.hasErrors()
        partner.errors.getFieldError('reference')
        partner.count() == 0
    }

    void 'test domain class validation with valid data'() {
        when:
        Partner partner = new Partner(name: "Test Company2", reference: "f84yt7", locale: Locale.ENGLISH, expirationTime: new Date())
        partner.save()

        then:
        partner.count() == 1
    }
}
