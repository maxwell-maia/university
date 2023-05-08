clear;

%birth and death rate is proportional to population size (number)

%shark growth = k1 ST
%shark death = k2 S
%tuna birth-death = k3 S
%tuna death = k4 ST

%ODE's
% S* = k1ST -k2S
% T* = k3T - k4ST
% these will go in the function file
% k1-4 are just constants declared below
% Where there is an S or a T, we need to access the previous value of 
% S* or T*, this is available in the x variable
% In the model function, if you output the result 
% of the equation: S* into y(1) then it (S) is accessible in the function 
% for the next iteration as x(1)

%Parameter values
% make k globally accessible
global k;
% set k (has to be after global k;)
k = [0.015; 0.7; 0.5; 0.01];

[t, y] = ode45(@ppmodel, 0:50, [100 100]);
plot(t, y, '-ob');
