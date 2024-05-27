package de.fhws.fiw.fds.project.server.api.states.partneruni;

import de.fhws.fiw.fds.project.Start;

public interface PartnerUniUri {

    String PATH_ELEMENT = "partnerUni";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";

}
