package com.example.james.wodrecordapp.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by jimmy on 03/02/2018.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
