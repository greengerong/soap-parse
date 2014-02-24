package green.github.greengerong.soap.parse.model;


public class NominationStatu {
    private String nominationId;
    private String agentId;
    private String transCode;
    private String actionCode;
    private String errorCode;
    private Statue statue;
    private String dateTime;

    public String getNominationId() {
        return nominationId;
    }

    public void setNominationId(String nominationId) {
        this.nominationId = nominationId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Statue getStatue() {
        return statue;
    }

    public void setStatue(Statue statue) {
        this.statue = statue;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}


