package search.configs;


import search.logger.ServiceLogger;

public class IDMConfigs {
    //idm configs
    private String idmScheme;
    private String idmHostName;
    private String idmPort;
    private String idmPath;
    //idm Endpoints
    private String privilege;

    public IDMConfigs() { }

    public IDMConfigs(ConfigsModel cm) throws NullPointerException {
        if (cm == null) {
            throw new NullPointerException("Unable to create MovieConfigs from ConfigsModel.");
        }
        else {
            idmScheme = cm.getIdmConfig().get("scheme");
            if (idmScheme == null) {
                System.err.println("No IDM scheme found in configuration file.");
            } else {
                System.err.println("IDM scheme settings: " + idmScheme);
            }

            idmHostName = cm.getIdmConfig().get("hostName");
            if (idmHostName == null) {
                System.err.println("No IDM hostname found in configuration file.");
            } else {
                System.err.println("IDM hostname settings: " + idmHostName);
            }

            idmPort = cm.getIdmConfig().get("port");
            if (idmPort == null) {
                System.err.println("No IDM port found in configuration file.");
            } else {
                System.err.println("IDM port settings: " + idmPort);
            }

            idmPath = cm.getIdmConfig().get("path");
            if (idmPath == null) {
                System.err.println("No IDM path found in configuration file.");
            } else {
                System.err.println("IDM path settings: " + idmPath);
            }

            privilege = cm.getIdmEndpoints().get("privilege");
            if (privilege == null) {
                System.err.println("No privilege found in configuration file.");
            } else {
                System.err.println("Privilege settings: " + privilege);
            }
        }
    }

    public void currentConfigs() {
        ServiceLogger.LOGGER.config("Scheme: " + idmScheme);
        ServiceLogger.LOGGER.config("Scheme: " + idmHostName);
        ServiceLogger.LOGGER.config("Scheme: " + idmPath);
        ServiceLogger.LOGGER.config("Scheme: " + idmPort);
        ServiceLogger.LOGGER.config("Scheme: " + privilege);
    }
    public String getIdmUri() {
        return idmScheme + idmHostName + ":" + idmPort + idmPath;
    }

    public String getPrivilegePath() {
        return privilege;
    }
}
