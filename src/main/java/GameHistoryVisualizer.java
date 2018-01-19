import ethnicity.EthnicGroup;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;


/**
 * Game history visualizer.
 * Provides GUI window with line chart (using JFreeChart)
 * containing wealthiness of community and ethnic groups
 * during the simulation.
 *
 * @author Vojtěch Kinkor
 */
public class GameHistoryVisualizer {

    /**
     * Game history visualizer.
     * Provides GUI window with line chart (using JFreeChart)
     * containing wealthiness of community and ethnic groups
     * during the simulation.
     */
    public static void visualize(GameHistory gameHistory) {
        javax.swing.SwingUtilities.invokeLater(() -> {

            JFreeChart chart = createChart(createDataset(gameHistory));
            ChartPanel cp = new ChartPanel(chart);

            cp.setPreferredSize(new Dimension(1000, 600));
            cp.setMaximumDrawWidth(4000);
            cp.setMaximumDrawHeight(4000);

            // save chart to PNG
            /*try {
                ChartUtilities.saveChartAsPNG(new File("jsim_gauss.png"), chart, 1000, 600);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            //Create and set up the window.
            JFrame frame = new JFrame("Prisoners Dilemma");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(cp);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });
    }

    /**
     * Creates a chart.
     *
     * @param dataset The data for the chart.
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Prisoners dilemma",        // chart title
                "Iteration",                // x axis label
                "Wealth",                   // y axis label
                dataset,                    // data
                PlotOrientation.VERTICAL,
                true,                       // include legend
                true,                       // tooltips
                false                       // urls
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(42, 42, 42));
        renderer.setSeriesPaint(1, new Color(255, 0, 0));
        renderer.setSeriesPaint(2, new Color(0, 0, 255));
        renderer.setSeriesPaint(3, new Color(0, 192, 0));
        renderer.setSeriesPaint(4, new Color(255, 150, 0));
        renderer.setSeriesPaint(5, new Color(0, 150, 190));
        renderer.setSeriesPaint(6, new Color(147, 0, 167));
        renderer.setSeriesPaint(7, new Color(134, 203, 0));
        renderer.setSeriesPaint(8, new Color(0, 131, 118));

        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        //final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }

    /**
     * Creates a dataset based on game history.
     * @param gameHistory Game history object with recorded simulation.
     * @return Dataset.
     */
    private static XYDataset createDataset(GameHistory gameHistory) {

        XYSeriesCollection dataset = new XYSeriesCollection();

        // add wealth of community
        {
            XYSeries series = new XYSeries("Community");
            dataset.addSeries(series);

            List<Integer> communityWealth = gameHistory.getCommunityWealth();

            // wealth in nth iteration
            for (int i = 0; i < communityWealth.size(); i++) {
                series.add(i, communityWealth.get(i));
            }
        }

        // add wealth of individual ethnic groups
        for (Map.Entry<EthnicGroup, List<GameHistory.EthnicGroupRecord>> entry : gameHistory.getEthnicGroupHistory().entrySet()) {

            EthnicGroup ethnicGroup = entry.getKey();
            List<GameHistory.EthnicGroupRecord> records = entry.getValue();

            XYSeries series = new XYSeries(ethnicGroup.getName());
            dataset.addSeries(series);

            // wealth in nth iteration
            for (int i = 0; i < records.size(); i++) {
                GameHistory.EthnicGroupRecord record = records.get(i);
                series.add(i, record.wealth);
            }
        }

        return dataset;
    }


}
