package symphys.symphys.fields.sim;

public class Simulation {
    boolean gravity_on=false, electrostatics_on=false;
    double t=0;
    public double dt=0.1;
    Body[] bodies;
    public BodyState[] states;

    public void step() {
        t += dt;
        BodyState[] newStates = new BodyState[states.length];
        for (int i=0; i!=states.length; ++i) {
            newStates[i] = states[i].copy();
            Wektor force = new Wektor();
            if (gravity_on) force = force.add(newStates[i].calcForce(states, Physics::calcGravityForce));
            if (electrostatics_on) force = force.add(newStates[i].calcForce(states, Physics::calcCoulombForce));
            newStates[i].applyForce(force, dt);
        }
        states = newStates;
    }

    void dumpPositions() {
        System.out.println("----\nt="+t);
        for (int i=0; i<states.length; ++i) {
            System.out.println(i+": "+states[i].position);
        }
    }
}
