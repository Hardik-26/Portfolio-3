package de.fhws.fiw.fds.project.server.api.states.dispatcher;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;
import de.fhws.fiw.fds.project.server.api.states.partnerUni.PartnerUniRelTypes;
import de.fhws.fiw.fds.project.server.api.states.partnerUni.PartnerUniUri;
import jakarta.ws.rs.core.Response;

public class GetDispatcher extends AbstractGetDispatcherState<Response> {

    public GetDispatcher(ServiceContext serviceContext) {
        super(serviceContext);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.GET_ALL_PERSONS, getAcceptRequestHeader());
        addLink(PartnerUniUri.REL_PATH, PartnerUniRelTypes.CREATE_PERSON, getAcceptRequestHeader());
    }
}
