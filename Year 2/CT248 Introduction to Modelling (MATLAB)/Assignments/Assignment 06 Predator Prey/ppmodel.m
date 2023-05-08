function y = ppmodel(t,x)
    % access k
    global k;

    y = [0;0];
    % the first slot is the equation for S
    % the second slot is the equation for T
    % I think these are the actual amounts of the population at a
    % particular time

    % Sharks
    y(1) = k(1)*x(1)*x(2) - k(2)*x(1);
    % x(1) is the first equation of integration that will be formed in the
    % call to ode45, x(1) represents the amount of Sharks
    % x(2) represents the amount of tuna

    % Tuna
    y(2) = k(3)*x(2) - k(4)*x(1)*x(2);
    % k is a column vector that has the paramter values given for the run 
    % of the model
    % k is declared as global
end
