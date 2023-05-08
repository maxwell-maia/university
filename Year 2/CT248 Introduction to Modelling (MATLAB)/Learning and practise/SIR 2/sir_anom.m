clear;
N = 10000;
alpha = 0.25;
infectivity = 0.125;
contacts  = 4;

time_vec = 0:.25:100;
init_vec   = [9999 1 0];

sir = @(t,x,c,N,i,alpha)[-c*x(1)*x(2)/N*i; ...
                                     c*x(1)*x(2)/N*i - alpha*x(2); ...
                                     alpha*x(2)];

[t,y] = ode45(sir, ...
                     time_vec, ...
                     init_vec,...
                     odeset, ...
                     contacts, ...
                     N, ...
                     infectivity,...
                     alpha);

plot(t,y);
