//Assignment -
//Sun Wu Choi
//COSC 237-004
//Spring, 2019

package rate;

// @author Sun Wu Choi
 
public abstract class VehicleRates {
    
    private double daily_rate;
    private double weekly_rate;
    private double monthly_rate;
    private double milage_rate;
    private double daily_insurance_rate;

    public VehicleRates(double daily, double weekly, double monthly, double milage, double insurance) {
        this.daily_rate = daily;
        this.weekly_rate = weekly;
        this.monthly_rate = monthly;
        this.milage_rate = milage;
        this.daily_insurance_rate = insurance;
    }

    public class Cost {

        private double daily_rate;
        private double weekly_rate;
        private double monthly_rate;
        private double milage_rate;
        private double daily_insurance_rate;

        public Cost(double daily, double weekly, double monthly, double milage, double insurance) {
            this.daily_rate = daily;
            this.weekly_rate = weekly;
            this.monthly_rate = monthly;
            this.milage_rate = milage;
            this.daily_insurance_rate = insurance;
        }

        public double getDaily_rate() {
            return daily_rate;
        }

        public double getWeekly_rate() {
            return weekly_rate;
        }

        public double getMonthly_rate() {
            return monthly_rate;
        }

        public double getMilage_rate() {
            return milage_rate;
        }

        public double getDaily_insurance_rate() {
            return daily_insurance_rate;
        }
    }

    public Cost cloneAsCostType() {
        return new Cost(this.daily_rate, this.weekly_rate, this.monthly_rate, this.milage_rate, this.daily_insurance_rate);
    }

    public double getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(double daily_rate) {
        this.daily_rate = daily_rate;
    }

    public double getWeekly_rate() {
        return weekly_rate;
    }

    public void setWeekly_rate(double weekly_rate) {
        this.weekly_rate = weekly_rate;
    }

    public double getMonthly_rate() {
        return monthly_rate;
    }

    public void setMonthly_rate(double monthly_rate) {
        this.monthly_rate = monthly_rate;
    }

    public double getMilage_rate() {
        return milage_rate;
    }

    public void setMilage_rate(double milage_rate) {
        this.milage_rate = milage_rate;
    }

    public double getDaily_insurance_rate() {
        return daily_insurance_rate;
    }

    public void setDaily_insurance_rate(double daily_insurance_rate) {
        this.daily_insurance_rate = daily_insurance_rate;
    }
    
    
}
