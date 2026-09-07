package utilities;

/**
 * This CommonSetting class help in generate results
 */
public class CommonSettings {

    private String appType;
    private String appEnvironment;
    private String projectName;
    private String emailOutput;
    private String emailId;
    private String htmlReport;
    private String xlsReport;
    private String testLogs;
    private String executionEnv;
    private String cloudProvider;
    private String hostName;
    private String key;
    private String remoteOS;
    private String buildNumber;
    private String browser;
    private String url;
    private String manageToolName;
    private String testlinkTool;
    private String testLinkHostName;
    private String testlinkAPIKey;
    private String testlinkProjectName;
    private String testlinkPlanName;
    private String bugToolName;
    private String bugTool;
    private String bugToolHostName;
    private String bugToolUserName;
    private String bugToolPassword;
    private String bugToolProjectName;
    private String email;
    private String password;

    public CommonSettings(String projectName, String appType, String appEnvironment, String emailOutput, String emailId,
                          String htmlReport, String xlsReport, String testLogs) {
        super();
        this.projectName = projectName;
        this.appType = appType;
        this.appEnvironment = appEnvironment;
        this.emailOutput = emailOutput;
        this.emailId = emailId;
        this.htmlReport = htmlReport;
        this.xlsReport = xlsReport;
        this.testLogs = testLogs;
    }

    public CommonSettings() {
        super();
    }

    public String getAppType() { return appType; }
    public void setAppType(String appType) { this.appType = appType; }

    public String getAppEnvironment() { return appEnvironment; }
    public void setAppEnvironment(String appEnvironment) { this.appEnvironment = appEnvironment; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getEmailOutput() { return emailOutput; }
    public void setEmailOutput(String emailOutput) { this.emailOutput = emailOutput; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getHtmlReport() { return htmlReport; }
    public void setHtmlReport(String htmlReport) { this.htmlReport = htmlReport; }

    public String getXlsReport() { return xlsReport; }
    public void setXlsReport(String xlsReport) { this.xlsReport = xlsReport; }

    public String getTestLogs() { return testLogs; }
    public void setTestLogs(String testLogs) { this.testLogs = testLogs; }

    public String getExecutionEnv() { return executionEnv; }
    public void setExecutionEnv(String executionEnv) { this.executionEnv = executionEnv; }

    public String getCloudProvider() { return cloudProvider; }
    public void setCloudProvider(String cloudProvider) { this.cloudProvider = cloudProvider; }

    public String getHostName() { return hostName; }
    public void setHostName(String hostName) { this.hostName = hostName; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getRemoteOS() { return remoteOS; }
    public void setRemoteOS(String remoteOS) { this.remoteOS = remoteOS; }

    public String getBuildNumber() { return buildNumber; }
    public void setBuildNumber(String buildNumber) { this.buildNumber = buildNumber; }

    public String getBrowser() { return browser; }
    public void setBrowser(String browser) { this.browser = browser; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getManageToolName() { return manageToolName; }
    public void setManageToolName(String manageToolName) { this.manageToolName = manageToolName; }

    public String getTestlinkTool() { return testlinkTool; }
    public void setTestlinkTool(String testlinkTool) { this.testlinkTool = testlinkTool; }

    public String getTestLinkHostName() { return testLinkHostName; }
    public void setTestLinkHostName(String testLinkHostName) { this.testLinkHostName = testLinkHostName; }

    public String getTestlinkAPIKey() { return testlinkAPIKey; }
    public void setTestlinkAPIKey(String testlinkAPIKey) { this.testlinkAPIKey = testlinkAPIKey; }

    public String getTestlinkProjectName() { return testlinkProjectName; }
    public void setTestlinkProjectName(String testlinkProjectName) { this.testlinkProjectName = testlinkProjectName; }

    public String getTestlinkPlanName() { return testlinkPlanName; }
    public void setTestlinkPlanName(String testlinkPlanName) { this.testlinkPlanName = testlinkPlanName; }

    public String getBugToolName() { return bugToolName; }
    public void setBugToolName(String bugToolName) { this.bugToolName = bugToolName; }

    public String getBugTool() { return bugTool; }
    public void setBugTool(String bugTool) { this.bugTool = bugTool; }

    public String getBugToolHostName() { return bugToolHostName; }
    public void setBugToolHostName(String bugToolHostName) { this.bugToolHostName = bugToolHostName; }

    public String getBugToolUserName() { return bugToolUserName; }
    public void setBugToolUserName(String bugToolUserName) { this.bugToolUserName = bugToolUserName; }

    public String getBugToolPassword() { return bugToolPassword; }
    public void setBugToolPassword(String bugToolPassword) { this.bugToolPassword = bugToolPassword; }

    public String getBugToolProjectName() { return bugToolProjectName; }
    public void setBugToolProjectName(String bugToolProjectName) { this.bugToolProjectName = bugToolProjectName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
