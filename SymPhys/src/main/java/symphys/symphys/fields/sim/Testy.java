package symphys.symphys.fields.sim;

import symphys.symphys.numerical.Wektor;

public class Testy {
    public static Simulation setuptest1() {
        Simulation simulation = new Simulation();
        simulation.electrostatics_on = true;
        simulation.bodies = new Body[2];
        simulation.bodies[0] = new Body();
        simulation.bodies[1] = new Body();
        simulation.bodies[0].mass = 1;
        simulation.bodies[1].mass = 5;
        simulation.bodies[0].charge = 0.0001;
        simulation.bodies[1].charge = -0.0001;
        simulation.states = new BodyState[2];
        simulation.states[0] = new BodyState(simulation.bodies[0]);
        simulation.states[1] = new BodyState(simulation.bodies[1]);
        simulation.states[1].position = new Wektor(3, 0);
        return simulation;
    }

    public static void test1() {
        Simulation simulation = setuptest1();
        simulation.dumpPositions();

        for (int i=0; i<10; ++i) {
            simulation.step();
            simulation.dumpPositions();
        }
    }

    public static void main(String[] args) {
        test1();
    }
}
