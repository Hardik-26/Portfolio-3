// Done

package de.fhws.fiw.fds.project;

import de.fhws.fiw.fds.sutton.server.AbstractStart;

public class Start extends AbstractStart {

    public static final String CONTEXT_PATH = "project";

    public static void main(String[] args) throws Exception {
        new Start().startTomcat();
    }

    @Override
    protected String contextPath() {
        return CONTEXT_PATH;
    }
}