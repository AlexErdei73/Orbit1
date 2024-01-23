import java.text.DecimalFormat;

public class Orbit1 {
    double totalEnergy;
    Plot orbit;
    private void euler(double x0, double y0, double vx0, double vy0, double dt, double tMax) {
        DecimalFormat fourDecimalDigits = new DecimalFormat("0.0000");
        double t = 0;
        double x, y, vx, vy, ax, ay;
        x = x0;
        y = y0;
        vx = vx0;
        vy = vy0;
        this.totalEnergy = this.totalE(x, y, vx, vy);
        System.out.print("Energy: ");
        System.out.println(fourDecimalDigits.format(this.totalEnergy));
        while(t < tMax) {
            ax = this.ax(x, y);
            ay = this.ay(x, y);
            x += vx * dt;
            y += vy * dt;
            vx += ax * dt;
            vy += ay * dt;
            t += dt;
            this.orbit.addPoint(x, y);
            this.totalEnergy = this.totalE(x, y, vx, vy);
        }
        System.out.print("Energy: ");
        System.out.println(fourDecimalDigits.format(this.totalEnergy));
    }
    private void eulerRichardson(double x0, double y0, double vx0, double vy0, double dt, double tMax) {
        DecimalFormat fourDecimalDigits = new DecimalFormat("0.0000");
        double t = 0;
        double x, xMid, y, yMid, vx, vxMid,  vy, vyMid, ax, axMid, ay, ayMid ;
        x = x0;
        y = y0;
        vx = vx0;
        vy = vy0;
        this.totalEnergy = this.totalE(x, y, vx, vy);
        System.out.print("Energy: ");
        System.out.println(fourDecimalDigits.format(this.totalEnergy));
        while(t < tMax) {
            ax = this.ax(x, y);
            ay = this.ay(x, y);
            xMid = x + vx*0.5*dt;
            yMid = y + vy*0.5*dt;
            vxMid = vx + ax*0.5*dt;
            vyMid = vy + ay*0.5*dt;
            axMid = this.ax(xMid, yMid);
            ayMid = this.ay(xMid, yMid);
            x += vxMid * dt;
            y += vyMid * dt;
            vx += axMid * dt;
            vy += ayMid * dt;
            t += dt;
            this.orbit.addPoint(x, y);
            this.totalEnergy = this.totalE(x, y, vx, vy);
        }
        System.out.print("Energy: ");
        System.out.println(fourDecimalDigits.format(this.totalEnergy));
    }
    Orbit1(double x0, double y0, double vx0, double vy0, double dt) {
        this.orbit = new Plot("Orbit", -4, 4, 0.2, -4, 4, 0.2);
        this.orbit.setPointSize(1);
        eulerRichardson(x0, y0, vx0, vy0, dt, 7);
    }

    private double ax(double x, double y) {
        double G = 4 * Math.PI * Math.PI;
        return -G * x * Math.pow((x * x + y * y), -1.5);
    }

    private double ay(double x, double y) {
        double G = 4 * Math.PI * Math.PI;
        return -G * y * Math.pow((x * x + y * y), -1.5);
    }

    private double totalE(double x, double y, double vx, double vy) {
        double G = 4 * Math.PI * Math.PI;
        double kinE = 0.5 * (vx * vx + vy * vy);
        double potE = - G / Math.sqrt(x * x + y * y);
        return kinE + potE;
    }
    public static void main(String[] args) {
        new Orbit1(1.5, 0, 0, 2 * Math.PI / Math.sqrt(1.5), 0.001);
    }


}