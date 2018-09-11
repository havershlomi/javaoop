package com.mmn12.q2;

public class ServiceChargeChecking extends CheckingAccount {

    final double SERVICE_CHARGE = 10;
    private double serviceCharge = SERVICE_CHARGE;

    public ServiceChargeChecking(String id, String accountId, String ownerName, double balance, double serviceCharge) {
        super(id, accountId, ownerName, balance);
        this.serviceCharge = serviceCharge;
    }

    public ServiceChargeChecking(String id, String accountId, String ownerName, double balance) {
        super(id, accountId, ownerName, balance);
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    @Override
    public void monthlyManage() throws IllegalBalance {
        this.withdraw(serviceCharge);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Service charge: " + this.serviceCharge;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)){
            if(!(obj instanceof ServiceChargeChecking))
                return false;
            ServiceChargeChecking serviceChargeChecking = (ServiceChargeChecking)obj;
            if(serviceChargeChecking.serviceCharge == this.serviceCharge)
                return true;
        }
        return false;
    }
}
