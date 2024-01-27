import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempConverter extends JFrame {

    private JTextField temperatureField;
    private JComboBox<String> unitComboBox;
    private JTextArea resultArea;

    public TempConverter() {
        // Set up the main frame
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and place components in the frame
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        // Make the frame visible
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel temperatureLabel = new JLabel("Enter Temperature:");
        temperatureLabel.setBounds(20, 20, 150, 25);
        panel.add(temperatureLabel);

        temperatureField = new JTextField();
        temperatureField.setBounds(180, 20, 150, 25);
        panel.add(temperatureField);

        JLabel unitLabel = new JLabel("Select Unit:");
        unitLabel.setBounds(20, 60, 150, 25);
        panel.add(unitLabel);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitComboBox = new JComboBox<>(units);
        unitComboBox.setBounds(180, 60, 150, 25);
        panel.add(unitComboBox);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(20, 100, 150, 25);
        panel.add(convertButton);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 140, 350, 100);
        resultArea.setEditable(false);
        panel.add(resultArea);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(temperatureField.getText());
            String originalUnit = unitComboBox.getSelectedItem().toString().toUpperCase();

            TempConverter tempConverter = new TempConverter();  // Create an instance of TempConverter

            double celsius, fahrenheit, kelvin;

            if (originalUnit.equals("CELSIUS")) {
                celsius = temperature;
                fahrenheit = tempConverter.celsiusToFahrenheit(celsius);
                kelvin = tempConverter.celsiusToKelvin(celsius);
            } else if (originalUnit.equals("FAHRENHEIT")) {
                fahrenheit = temperature;
                celsius = tempConverter.fahrenheitToCelsius(fahrenheit);
                kelvin = tempConverter.fahrenheitToKelvin(fahrenheit);
            } else if (originalUnit.equals("KELVIN")) {
                kelvin = temperature;
                celsius = tempConverter.kelvinToCelsius(kelvin);
                fahrenheit = tempConverter.kelvinToFahrenheit(kelvin);
            } else {
                resultArea.setText("Invalid unit of measurement. Please enter Celsius, Fahrenheit, or Kelvin.");
                return;
            }

            resultArea.setText("\nConverted Temperatures:\n" +
                    "In Celsius: " + celsius + " °C\n" +
                    "In Fahrenheit: " + fahrenheit + " °F\n" +
                    "In Kelvin: " + kelvin + " K");

        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid temperature value. Please enter a valid number.");
        }
    }

    // Instance methods for conversion
    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) * 5 / 9;
    }

    public double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public double kelvinToFahrenheit(double kelvin) {
        return (kelvin * 9 / 5) - 459.67;
    }

    public static void main(String[] args) {
        new TempConverter();
    }
}
