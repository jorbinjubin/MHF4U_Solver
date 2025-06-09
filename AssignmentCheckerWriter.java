
/**
 * @author Justin Jiang
 * @version 1.11
 * Description: MHF4UP Vectors assignment solver
 * READ: Run the program, input your student number as it is, and get the answers to ALL of the questions! 
 * DISCLAIMER: I do not bear responsibility if you lose marks. This program should only be used as a reference. 
 * Problems which only provide answers in decimal are there to check your answer against. Use a calculator to 
 * find the decimal representation of your answer, and check it with the output.
 */
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.*;

public class AssignmentCheckerWriter {    
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.close();

        PrintWriter pw = new PrintWriter(new FileWriter("output/" + s + "_Vector_Assignment_Solutions.txt"));
        int[] sN = new int[9];
        // Create student number int array
        for (int i = 0; i < 9; i++) {
            sN[i] = Integer.parseInt(s.charAt(i) + "");
        }

        // Negate the required coordinates
        sN[1] *= -1;
        sN[3] *= -1;
        sN[5] *= -1;
        sN[7] *= -1;

        // Create points
        Point A = new Point(sN[0], sN[1], sN[2]);
        Point B = new Point(sN[3], sN[4], sN[5]);
        Point C = new Point(sN[6], sN[7], sN[8]);
        Point O = new Point(0, 0, 0);

        // Create displacement vectors
        Vector AB = new Vector(A, B);
        Vector AC = new Vector(A, C);
        Vector BC = new Vector(B, C);

        Vector OA = new Vector(A, O);
        Vector OB = new Vector(B, O);
        Vector OC = new Vector(C, O);

        /**
         * Problem 1: Points A, B, C
         */
        pw.println("Problem 1: Points A, B, and C");
        pw.println("Point A: " + A.toString());
        pw.println("Point B: " + B.toString());
        pw.println("Point C: " + C.toString());
        pw.println();

        /**
         * Problem 2: Displacement Vector AB
         */
        pw.println("Problem 2: Displacement Vector AB");
        pw.println("Vector AB: " + AB.toString());
        pw.println("Vector AC: " + AC.toString());
        pw.println("Vector BC: " + BC.toString());
        pw.println();

        /**
         * Problem 3: Perimeter of Triangle
         */
        pw.println("Problem 3: Perimeter of Triangle ABC");
        pw.printf("Decimal: %.2f", AB.magnitude + BC.magnitude + AC.magnitude);
        pw.println();
        pw.printf("Exact: sqrt(%.0f) + sqrt(%.0f) + sqrt(%.0f)", Math.pow(AB.magnitude, 2),
                Math.pow(AC.magnitude, 2), Math.pow(BC.magnitude, 2));
        pw.println();
        pw.println();

        /**
         * Problem 4: Angle A of triangle ABC
         */
        pw.println("Problem 4: Angle A of triangle ABC");
        pw.printf("Angle A: %.2f",
                Math.acos((AC.magnitude * AC.magnitude + AB.magnitude * AB.magnitude - BC.magnitude * BC.magnitude)
                        / (2 * AC.magnitude * AB.magnitude)) * 180 / Math.PI);
        pw.println();
        pw.println();

        /**
         * Problem 5: Area of Triangle ABC
         * Note: I will use half the cross product magnitude, but a solution using
         * Heron's formula or 1/2BH is also equally valid.
         */
        pw.println("Problem 5: Area of Triangle ABC");
        pw.printf("Area: sqrt(%.0f)/2", Math.pow(AB.crossProduct(AC).magnitude, 2)); // Already positive due to
                                                                                             // squaring
        pw.println();
        pw.println();

        /**
         * Problem 6: Volume of OABC
         * Note: I will solve this problem using the property that the volume of a
         * triangular base pyramid enclosed within a parallelipiped is 1/6th the volume
         * of the parallelipiped.
         * Explanation: The area of a pyramid is 1/3BH. The area of the base is 1/2 of
         * the area of a face of the parallelipiped. 1/3(b/2)H = 1/6BH.
         * The volume of the parallelipiped can be found using the absolute value of the
         * scalar triple product: |OA * (OB x OC)| where * is the dot product and 'x' is
         * the cross product.
         */
        pw.println("Problem 6: Volume of OABC");

        pw.printf("Volume: %.0f/6", Math.abs(OA.dotProduct(OB.crossProduct(OC))));
        pw.println();
        pw.println();

        /**
         * Problem 7: Unit vector of median from A to BC
         */

        pw.println("Problem 7: Unit vector of median from A to BC");
        Point M = new Point(B.x + BC.x / 2, B.y + BC.y / 2, B.z + BC.z / 2);
        pw.println("Midpoint of side: " + M);
        Vector AM = new Vector(A, M);
        pw.println("Vector AM: " + AM);
        Vector AM_Norm = new Vector(AM.x / AM.magnitude, AM.y / AM.magnitude, AM.z / AM.magnitude);
        pw.printf("I believe in you. You can normalize the vector. It\'s magnitude is sqrt(%s)\n",
                AM.magnitude * AM.magnitude);
        pw.println("Decimal: " + AM_Norm);
        pw.println("(might be off by a bit)");
        pw.println();

        /**
         * Problem 8: Shortest distance from origin to triangle ABC (optimized method)
         */
        pw.println("Problem 8: Shortest distance from origin to triangle ABC");
        double volume = Math.abs(OA.dotProduct(OB.crossProduct(OC))) / 6.0;
        double area = AB.crossProduct(AC).magnitude / 2.0;
        double distance = (3 * volume) / area;
        pw.printf("Decimal: %.3f%n", distance);
        pw.printf("Exact form: %.0f / sqrt(%.0f)%n", Math.abs(OA.dotProduct(OB.crossProduct(OC))),
                4 * (area * area));
        pw.println();

        /**
         * Problem 9: Height from A to BC
         */
        pw.println("Problem 9: Unit vector of the height from A to BC");

        Vector BA = new Vector(B, A);
        Vector p9v = BA.crossProduct(BC).crossProduct(BC);
        p9v.normalize();
        pw.println(p9v);
        pw.println();
        

        /**
         * Problem 10: Unit vector of the perpendicular bisector of BC
         * Note that this is the same exact vector as the one from Problem 9
         * since vectors do not have a defined start or end point, only components or direction and magnitude.
         */

        pw.println("Problem 10: Unit vector of the perpendicular bisector of BC");
        Vector p10v = BA.crossProduct(BC).crossProduct(BC);
        p10v.normalize();
        pw.println(p10v);
        pw.println();

        /**
         * Problem 11: Unit vector of angle bisector of <A
         * Note: Normalize AB and normalize AC to get a rhombus with sides AB and AC, with diagonal AB+AC
         * Then normalize AB+AC
         */
        pw.println("Problem 11: Unit vector of angle bisector of <A");
        Vector p11AB = new Vector(A, B);
        Vector p11AC = new Vector(A, C);

        p11AB.normalize();
        p11AC.normalize();

        Vector p11Diag = p11AB.add(p11AC);

        p11Diag.normalize();
        pw.println(p11Diag);

        pw.flush();
        // look how trivial p11 is with code
    }
}

class Vector {
    double x, y, z;
    double magnitude;

    Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        magnitude = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    Vector(Point p1, Point p2) {
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
        this.z = p2.z - p1.z;
        magnitude = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    double dotProduct(Vector v2) {
        return v2.x * x + v2.y * y + v2.z * z;
    }

    Vector crossProduct(Vector v2) {
        return new Vector(this.y * v2.z - this.z * v2.y, this.z * v2.x - this.x * v2.z, this.x * v2.y - this.y * v2.x);
    }

    public String toString() {
        if ((int) x == x && (int) y == y && (int) z == z) {
            return "[" + (int) x + ", " + (int) y + ", " + (int) z + "]";
        }
        return String.format("[%.4f, %.4f, %.4f]", x, y, z);
    }

    Vector multiply(double d) {
        return new Vector(x * d, y * d, z * d);
    }

    Vector add(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
    }
    
    void normalize() {
        x = x/magnitude;
        y = y/magnitude;
        z = z/magnitude;
        magnitude = 1;
    }
}

class Point {
    double x, y, z;

    Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        if ((int) x == x && (int) y == y && (int) z == z) {
            return "(" + (int) x + ", " + (int) y + ", " + (int) z + ")";
        }
        return "(" + x + ", " + y + ", " + z + ")";
    }
}