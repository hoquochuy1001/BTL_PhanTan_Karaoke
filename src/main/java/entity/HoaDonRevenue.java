package entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class HoaDonRevenue implements Serializable {
    private static final long serialVersionUID = 1L;  // Optional, but recommended for versioning

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    private int month;

    public int getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(int dayWeek) {
        this.dayWeek = dayWeek;
    }

    private int dayWeek;
    private String dayOfWeek;
    private BigDecimal totalRevenue;

    public HoaDonRevenue() {}

    public HoaDonRevenue(Integer month, BigDecimal totalRevenue) {
        this.month = month;

        this.totalRevenue = totalRevenue;
    }
    public HoaDonRevenue(String dayOfWeek, BigDecimal totalRevenue) {
        this.dayOfWeek = dayOfWeek;
        this.totalRevenue = totalRevenue;
    }
    public HoaDonRevenue(int dayWeek, String dayOfWeek, BigDecimal totalRevenue) {
        this.dayWeek = dayWeek;
        this.dayOfWeek = dayOfWeek;
        this.totalRevenue = totalRevenue;
    }
    @Override
    public String toString() {
        return "HoaDonRevenue{" +
                "month=" + month +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
