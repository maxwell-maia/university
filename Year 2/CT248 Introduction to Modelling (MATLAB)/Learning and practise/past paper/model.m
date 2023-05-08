function y = model(t,x,beta,gamma)
    y = [0;0;0];
    S = x(1);
    I = x(2);
    R = x(3);
    N = sum(x);
    y(1) = -beta/N*I*S;
    y(2) = beta/N*I*S - gamma*I;
    y(3) = gamma*I;
end