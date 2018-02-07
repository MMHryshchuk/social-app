package com.clearsoft.welivre.domain.api;

import com.clearsoft.welivre.domain.api.request.AssistancesRequest;
import com.clearsoft.welivre.domain.api.request.CrUpMotivationsRequest;
import com.clearsoft.welivre.domain.api.request.CrUpPlanRequest;
import com.clearsoft.welivre.domain.api.request.CravingsRequest;
import com.clearsoft.welivre.domain.api.request.GetPlanRequest;
import com.clearsoft.welivre.domain.api.request.PlanRequest;
import com.clearsoft.welivre.domain.api.request.TriggersRequest;
import com.clearsoft.welivre.domain.api.response.PlanDtoResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by on 18.07.17.
 */

public interface PlanApi {

    // TODO : body request
    @POST("mobile/planmanager/plan")
    Observable<Object> createUpdatePlan(@Body CrUpPlanRequest request);

    @POST("mobile/planmanager/getPlan")
    Observable<PlanDtoResponse> getPlan(@Body GetPlanRequest request);

    @POST("mobile/planmanager/setMotivations")
    Observable<Object> createUpdateMotivations(@Body CrUpMotivationsRequest request);

    @POST("mobile/planmanager/getMotivations")
    Observable<Object> getMotivations(@Body PlanRequest request);

    @POST("mobile/planmanager/setTriggers")
    Observable<Object> setTriggers(@Body TriggersRequest request);

    @POST("mobile/planmanager/getTriggers")
    Observable<Object> getTriggers(@Body PlanRequest request);

    @POST("mobile/planmanager/setCravings")
    Observable<Object> setCravings(@Body CravingsRequest request);

    @POST("mobile/planmanager/getCravings")
    Observable<Object> getCravings(@Body PlanRequest request);

    @POST("mobile/planmanager/setAssistances")
    Observable<Object> createUpdateAssistances(@Body AssistancesRequest request);

    @POST("mobile/planmanager/getAssistances")
    Observable<Object> getAssistances(@Body PlanRequest request);
}
