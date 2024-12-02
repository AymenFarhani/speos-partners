package speos.partners

import grails.validation.Validateable


class Partner implements Validateable{

    Long id
    String name
    String reference
    Locale locale
    Date expirationTime

    Partner(String name, String reference, Locale locale, Date expirationTime) {
        this.name = name
        this.reference = reference
        this.locale = locale
        this.expirationTime = expirationTime
    }

    static mapping = {
        name column: 'companyName'
        reference column: 'ref'
        locale column: 'locale'
        expirationTime column: 'expires'
    }

    static constraints = {
        name(nullable: false, blank: false, maxSize: 30)
        reference(nullable: false, blank: false, maxSize: 15)
        locale(nullable: true)
        expirationTime(nullable: true)
    }
}
