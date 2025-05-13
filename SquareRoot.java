public class SquareRoot {
    public static final double epsilon = 1e-7;

    public static double sqrt(double num, double epsilon){
        //Initializing variables
        double currentGuess = num;
        double previousGuess = 0.0;
        //Handling edge cases given numerical input
        if (Double.isNaN(num) || num < 0) {
            return Double.NaN;
        }
        if (num == 0 || num == Double.POSITIVE_INFINITY) {
            return num;
        }
        //Actual method body
        while (Math.abs(currentGuess - previousGuess) > epsilon){
                //Initializes desired variables and runs so long as the abs is beyond epsilon
                previousGuess = currentGuess;
                currentGuess = 0.5 * (previousGuess + (num / previousGuess));
                //Performs computation for each step of Babylonian method
        }
        return currentGuess;
    }

    public static void main (String[] args){
        //Handling misuse/invalid args
        if (args.length < 1 || args.length > 2){
            System.err.println("Usage: java SquareRoot <value> [epsilon]");
            System.exit(1);
        }

        double num;
        double OURepsilon = epsilon;

        try{
            num = Double.parseDouble(args[0]); //Taking value from command line
        } catch (NumberFormatException e){
            System.err.println("Error: Value argument must be a double.");
            System.exit(1);
            return;
        }
        //Now if user provides a value for epsilon
        if (args.length == 2){
            try{
                OURepsilon = Double.parseDouble(args[1]);
                //If epsilon is negative or zero
                if (OURepsilon <= 0){
                    System.err.println("Error: Epsilon argument must be a positive double.");
                    System.exit(1);
                }
            } catch (NumberFormatException e){
                //If epsilon cannot be parsed into a double
                System.err.println("Error: Epsilon argument must be a positive double.");
                System.exit(1);
            }
        }
        //Compute the square root given Babylonian method
        double answer = sqrt(num, OURepsilon);
        //Print result to the nearest 8 decimal places
        System.out.printf("%.8f%n", answer);
    }
}
