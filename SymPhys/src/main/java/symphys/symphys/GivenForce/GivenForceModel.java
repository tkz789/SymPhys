package symphys.symphys.GivenForce;

import symphys.symphys.SimulationModel;
import symphys.symphys.numerical.RungeKutta4;
import symphys.symphys.numerical.State;
import symphys.symphys.numerical.Wektor;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class GivenForceModel implements SimulationModel {


    State state;
    Function<State, Wektor> force;
    HashMap<String, Double> param;

    GivenForceModel(HashMap<String, Double> param){
        this.param = param;
        state = new State(new Wektor(param.get("x")*100, param.get("y")*100), new Wektor(param.get("v_x"), param.get("v_y")),0);
        force = state -> new Wektor(param.get("a") + param.get("b") * state.position.getX() + param.get("c")* state.position.getY(),
                param.get("d") + param.get("e") * state.position.getX() + param.get("f") * state.position.getY());
    }
    @Override
    public void step(double time) {
        state = RungeKutta4.step(state, time, param.get("mass"), force);
    }
    public Wektor getPositions(){
        return state.position;
    }
}
