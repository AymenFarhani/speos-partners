package speos.partners

import grails.converters.JSON

class HealthController {

    def getStatus() {
        render([status: "UP"] as JSON)
    }
}
