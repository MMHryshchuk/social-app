package com.clearsoft.welivre.domain.api.request;

/**
 * Created by on 20.07.17.
 */

public class CrUpPlanRequest {

    int user_id;
    String quit_date;
    String motivations_ids;
    String triggers_ids;
    String cravings_ids;
    String assistances_ids;
    String relapsed;

    public CrUpPlanRequest() {
    }

    public CrUpPlanRequest(int user_id, String quit_date, String motivations_ids, String triggers_ids, String cravings_ids, String assistances_ids, String relapsed) {
        this.user_id = user_id;
        this.quit_date = quit_date;
        this.motivations_ids = motivations_ids;
        this.triggers_ids = triggers_ids;
        this.cravings_ids = cravings_ids;
        this.assistances_ids = assistances_ids;
        this.relapsed = relapsed;
    }
}
