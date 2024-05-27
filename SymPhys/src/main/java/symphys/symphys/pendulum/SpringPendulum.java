package symphys.symphys.pendulum;

public class SpringPendulum extends Pendulum{
    SpringPendulum(SpringPendulumCalculator calculator, SpringPendulumBody body){
         this.calculator = calculator;
         this.body = body;
    }

}
