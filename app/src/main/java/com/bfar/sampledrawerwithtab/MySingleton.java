package com.bfar.sampledrawerwithtab;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by rmara on 3/1/15.
 */
public class MySingleton {
    private static MySingleton ourInstance = new MySingleton();

    private Bus FragmentTabBus = new Bus(ThreadEnforcer.ANY);
    private Bus FragmentHomeBus = new Bus(ThreadEnforcer.ANY);
    private Bus FragmentCategoryBus = new Bus(ThreadEnforcer.ANY);
    private Bus FragmentTopPaidBus = new Bus(ThreadEnforcer.ANY);

    public static MySingleton getInstance() {
        return ourInstance;
    }

    private MySingleton() {
    }

    public Bus getFragmentTabBus() {
        return FragmentTabBus;
    }

    public void setFragmentTabBus(Bus fragmentTabBus) {
        FragmentTabBus = fragmentTabBus;
    }

    public Bus getFragmentHomeBus() {
        return FragmentHomeBus;
    }

    public void setFragmentHomeBus(Bus fragmentHomeBus) {
        FragmentHomeBus = fragmentHomeBus;
    }

    public Bus getFragmentCategoryBus() {
        return FragmentCategoryBus;
    }

    public void setFragmentCategoryBus(Bus fragmentCategoryBus) {
        FragmentCategoryBus = fragmentCategoryBus;
    }

    public Bus getFragmentTopPaidBus() {
        return FragmentTopPaidBus;
    }

    public void setFragmentTopPaidBus(Bus fragmentTopPaidBus) {
        FragmentTopPaidBus = fragmentTopPaidBus;
    }
}
