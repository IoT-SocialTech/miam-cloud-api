package com.miam.cloudApi.miamCloudApi.application.serializable.ids;

import java.util.Objects;

public class CaregiversNursingHomesId {

    private int caregiver;
    private int nursingHome;

    public CaregiversNursingHomesId() {}

    public CaregiversNursingHomesId(int caregiver, int nursingHome) {
        this.caregiver = caregiver;
        this.nursingHome = nursingHome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiversNursingHomesId that = (CaregiversNursingHomesId) o;
        return caregiver == that.caregiver && nursingHome == that.nursingHome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(caregiver, nursingHome);
    }

}
