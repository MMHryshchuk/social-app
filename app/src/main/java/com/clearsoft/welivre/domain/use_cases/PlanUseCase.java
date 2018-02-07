package com.clearsoft.welivre.domain.use_cases;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.ui.dvo.HealthDvo;
import com.clearsoft.welivre.ui.dvo.MotivationDvo;
import com.clearsoft.welivre.ui.dvo.MyPlanDvo;
import com.clearsoft.welivre.ui.dvo.PlanDvo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by on 18.07.17.
 */

public interface PlanUseCase {

    Observable<MyPlanDvo> getMyPlan(App app);
    Observable<List<MotivationDvo>> getMotivationsToRecycle(App app);
    Observable<Object> createUpdatePlan(PlanDvo dvo);
    Observable<HealthDvo> getHealth();
}
