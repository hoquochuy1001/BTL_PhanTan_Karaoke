package bll;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.List;

public class HorizontalChart extends ApplicationFrame {

    public HorizontalChart(String title, List<Object[]> data, boolean isChart) {
        super(title);
        JFreeChart barChart = createChart(createDataset(data),isChart);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(List<Object[]> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add data to the dataset
        for (Object[] row : data) {
            String customerName = (String) row[0];  // Customer name
            Integer orderCount = (Integer) row[1];  // Number of orders
            System.out.println("customerName"+customerName);
            System.out.println("orderCount"+orderCount);



            // Add (or update) the value in the dataset
            dataset.addValue(orderCount, "Số lượng đơn hàng", customerName);  // Add to dataset
        }

        return dataset;
    }


    private JFreeChart createChart(DefaultCategoryDataset dataset,  boolean isChart) {
        String chartTitle = isChart
                ? "Top 5 Khách Hàng Đặt Hàng Nhiều"
                : "Top 5 Nhân Viên xuất sắc nhất";
        String xAxisLabel = isChart ? "Khách hàng" : "Nhân viên";

        JFreeChart chart = ChartFactory.createBarChart(
                chartTitle,                      // Tiêu đề
                xAxisLabel,                      // Nhãn trục X
                "Số lượng đơn hàng",             // Nhãn trục Y
                dataset,                         // Dataset
                PlotOrientation.HORIZONTAL,      // Hướng biểu đồ
                true,                            // Hiển thị chú thích (legend)
                true,                            // Hiển thị tooltip
                false                            // Không cần URL
        );

        // Customize plot and render
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(153, 50, 204)); // Purple color for bars

        // Set label font and size
        plot.getDomainAxis().setLabelFont(new Font("Arial", Font.BOLD, 14));
        plot.getRangeAxis().setLabelFont(new Font("Arial", Font.BOLD, 14));

        // Display the value of each bar at the end
        renderer.setDefaultItemLabelGenerator(new CategoryItemLabelGenerator() {
            @Override
            public String generateRowLabel(CategoryDataset categoryDataset, int i) {
                return null;
            }

            @Override
            public String generateColumnLabel(CategoryDataset categoryDataset, int i) {
                return null;
            }

            @Override
            public String generateLabel(CategoryDataset categoryDataset, int i, int i1) {
                if (categoryDataset instanceof DefaultCategoryDataset) {
                    DefaultCategoryDataset customDataset = (DefaultCategoryDataset) categoryDataset;
                    Number value = customDataset.getValue(i, i1); // Get value using series and category
                    return value == null ? "" : value.toString(); // Return value as string or empty if null
                } else {
                    return ""; // Fallback in case dataset is not DefaultCategoryDataset
                }
            }

        });



        renderer.setDefaultItemLabelsVisible(true);

        return chart;
    }


}
