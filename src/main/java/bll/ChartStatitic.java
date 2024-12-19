package bll;

import entity.HoaDon;
import entity.HoaDonRevenue;
import model.HoaDonDao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ChartStatitic {

    private HoaDonDao hoaDonDao; // Giả sử bạn có một DAO để lấy dữ liệu

    public ChartStatitic(HoaDonDao hoaDonDao) {
        this.hoaDonDao = hoaDonDao;
    }

    // Phương thức tạo dataset từ dữ liệu hóa đơn
    // Dataset cho doanh số theo tháng
    private DefaultCategoryDataset createMonthlyRevenueDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            List<HoaDonRevenue> revenueList = hoaDonDao.getDoanhThuTheoThang();
            if (revenueList != null) {
                for (HoaDonRevenue revenue : revenueList) {
                    dataset.addValue(revenue.getTotalRevenue(), "Doanh số", "Tháng " + revenue.getMonth());
                }
            } else {
                System.out.println("Dữ liệu doanh thu theo tháng không có.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    // Dataset cho doanh số theo ngày trong tuần
    private DefaultCategoryDataset createDailyRevenueDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            List<HoaDonRevenue> dailyRevenueList = hoaDonDao.getDoanhThuTheoNgayTrongTuan();
            if (dailyRevenueList != null) {
                // Duyệt qua từng kết quả và thêm vào dataset
                for (HoaDonRevenue revenue : dailyRevenueList) {
                    // Thêm giá trị vào dataset, với tên ngày từ dữ liệu cơ sở dữ liệu
                    System.out.println("Day of Week: " + revenue.getDayOfWeek() + ", Total Revenue: " + revenue.getTotalRevenue());

                    dataset.addValue(revenue.getTotalRevenue(), "Doanh thu theo ngày", revenue.getDayOfWeek());
                }
            } else {
                System.out.println("Dữ liệu doanh thu theo ngày không có.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return dataset;
    }


    // Phương thức tạo biểu đồ
    private JFreeChart createBarChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Thống kê doanh số", // Tiêu đề
                "Tháng",            // Nhãn trục X
                "Doanh số (VND)",   // Nhãn trục Y
                dataset             // Dữ liệu
        );
    }
    private JFreeChart createLineChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createLineChart(
                "Doanh thu theo ngày trong tuần",  // Tiêu đề
                "Ngày",                           // Nhãn trục X
                "Doanh thu (VND)",                // Nhãn trục Y
                dataset                           // Dữ liệu
        );
    }

    // Phương thức tạo và trả về JPanel chứa biểu đồ
    public JPanel createMonthlyRevenueChartPanel() {
        DefaultCategoryDataset dataset = createMonthlyRevenueDataset();
        JFreeChart chart = createBarChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 300));

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.BorderLayout());
        panel.add(chartPanel, java.awt.BorderLayout.CENTER);

        return panel;
    }

    public JPanel createDailyRevenueChartPanel() {
        DefaultCategoryDataset dataset = createDailyRevenueDataset();
        JFreeChart chart = createLineChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 300));

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.BorderLayout());
        panel.add(chartPanel, java.awt.BorderLayout.CENTER);

        return panel;
    }


}
