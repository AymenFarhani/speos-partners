package speos.partners

class UrlMappings {

    static mappings = {
        "/api/partners"(controller: "partner", action: "getPartners", method: "GET")
        "/api/partners/$id"(controller: "partner", action: "getPartnerById", method: "GET")
        "/api/partners"(controller: "partner", action: "createPartner", method: "POST")
        "/api/partners/$id"(controller: "partner", action: "updatePartner", method: "PUT")
        "/api/partners/$id"(controller: "partner", action: "deletePartnerById", method: "DELETE")
        "/api/health"(controller: "health", action: "getStatus")
    }
}
