package com.clearsoft.welivre.ui.dvo;

import java.util.List;

/**
 * Created by on 20.07.17.
 */

public class MyPlanDvo {
    private PlanDvo planDvo;
    private List<MotivationDvo> motivationDvoList;
    private List<CravingsDvo> cravingsDvoList;
    private List<AssistancesDvo> assistancesDvoList;
    private List<MotivationDvo> triggersDvoList;

    public MyPlanDvo() {
    }

    public MyPlanDvo(PlanDvo planDvo, List<MotivationDvo> motivationDvoList) {
        this.planDvo = planDvo;
        this.motivationDvoList = motivationDvoList;
    }

    public MyPlanDvo(PlanDvo planDvo, List<MotivationDvo> motivationDvoList, List<CravingsDvo> cravingsDvoList) {
        this.planDvo = planDvo;
        this.motivationDvoList = motivationDvoList;
        this.cravingsDvoList = cravingsDvoList;
    }

    public MyPlanDvo(PlanDvo planDvo, List<MotivationDvo> motivationDvoList, List<CravingsDvo> cravingsDvoList, List<AssistancesDvo> assistancesDvoList) {
        this.planDvo = planDvo;
        this.motivationDvoList = motivationDvoList;
        this.cravingsDvoList = cravingsDvoList;
        this.assistancesDvoList = assistancesDvoList;
    }

    public MyPlanDvo(PlanDvo planDvo, List<MotivationDvo> motivationDvoList, List<CravingsDvo> cravingsDvoList, List<AssistancesDvo> assistancesDvoList, List<MotivationDvo> triggersDvoList) {
        this.planDvo = planDvo;
        this.motivationDvoList = motivationDvoList;
        this.cravingsDvoList = cravingsDvoList;
        this.assistancesDvoList = assistancesDvoList;
        this.triggersDvoList = triggersDvoList;
    }

    public List<CravingsDvo> getCravingsDvoList() {
        return cravingsDvoList;
    }

    public void setCravingsDvoList(List<CravingsDvo> cravingsDvoList) {
        this.cravingsDvoList = cravingsDvoList;
    }

    public PlanDvo getPlanDvo() {
        return planDvo;
    }

    public void setPlanDvo(PlanDvo planDvo) {
        this.planDvo = planDvo;
    }

    public List<MotivationDvo> getMotivationDvoList() {
        return motivationDvoList;
    }

    public void setMotivationDvoList(List<MotivationDvo> motivationDvoList) {
        this.motivationDvoList = motivationDvoList;
    }

    public List<AssistancesDvo> getAssistancesDvoList() {
        return assistancesDvoList;
    }

    public void setAssistancesDvoList(List<AssistancesDvo> assistancesDvoList) {
        this.assistancesDvoList = assistancesDvoList;
    }

    public List<MotivationDvo> getTriggersDvoList() {
        return triggersDvoList;
    }

    public void setTriggersDvoList(List<MotivationDvo> triggersDvoList) {
        this.triggersDvoList = triggersDvoList;
    }
}
