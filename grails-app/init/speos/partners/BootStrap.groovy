package speos.partners

import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {

    PartnerService partnerService

    def init = { servletContext ->
        Partner firstPartner = new Partner("Speos", "rgf587", Locale.default,new Date())
        Partner secondPartner = new Partner("Speos", "gt5d8", Locale.default,new Date())
        Partner thirdPartner = new Partner("Speos", "iu655j", Locale.default,new Date())
        Partner fourthPartner = new Partner("Speos", "vghf54", Locale.default,new Date())
        Partner fifthPartner = new Partner("Speos", "ml3k26", Locale.default,new Date())
        Partner sixthPartner = new Partner("Speos", "cfs8d5", Locale.default,new Date())
        Partner seventhPartner = new Partner("Speos", "gtt5d8", Locale.default,new Date())
        Partner eighthPartner = new Partner("Speos", "iu655j", Locale.default,new Date())
        Partner ninthPartner = new Partner("Speos", "vgfl54", Locale.default,new Date())
        Partner tenthPartner = new Partner("Speos", "1ml326", Locale.default,new Date())

        partnerService.createPartner(firstPartner)
        partnerService.createPartner(secondPartner)
        partnerService.createPartner(thirdPartner)
        partnerService.createPartner(fourthPartner)
        partnerService.createPartner(fifthPartner)
        partnerService.createPartner(sixthPartner)
        partnerService.createPartner(seventhPartner)
        partnerService.createPartner(eighthPartner)
        partnerService.createPartner(ninthPartner)
        partnerService.createPartner(tenthPartner)
    }
    def destroy = {
    }
}
