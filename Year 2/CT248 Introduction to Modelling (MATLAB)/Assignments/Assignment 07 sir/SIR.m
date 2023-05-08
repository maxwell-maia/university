function y = SIR(t, x, c, i, alpha, beta, gamma)
    %extended SIR model

    %y(1) = S
    %y(2) = I
    %y(3) = R
    %y(4) = H
    %y(5) = RH (one thing)
    %N = x(1) + x(2) + x(3) + x(4) + x(5)
    
    %initialize y
    y = [0; 0; 0; 0; 0];

    %S
    % dS/dt = -c*S*(I/N)*i
    y(1) = -c*x(1)*(x(2)/ (x(1) + x(2) + x(3) + x(4) + x(5)) )*i;

    %I
    % dI/dt = c*S*(I/N)*i - alpha*I
    y(2) = c*x(1)*(x(2)/ (x(1) + x(2) + x(3) + x(4) + x(5)) )*i - alpha*x(2);

    %R
    % dR/dt = alpha*I - beta*R
    y(3) = alpha*x(2) - beta*x(3);

    %H
    % dH/dt = beta*R - gamma*H
    y(4) = beta*x(3) - gamma*x(4);

    %RH
    % dRH/dt = gamma*H
    y(5) = gamma*x(4);

    %N
    %N = S + I + R + H + RH
    %y(6) = x(1) + x(2) + x(3) + x(4) + x(5);
end


function y = SIR2(t, x, c, i, alpha, beta, gamma)
y = [0; 0; 0; 0; 0];
S = x(1);
I = x(2);
R = x(3);
H = x(4);
RH = x(5);
N = sum(x);

y(1) = -c*S*(I/N)*i;
y(2) = c*S*(I/N)*i;
y(3) = alpha*I - beta*R;
y(4) = beta*R - gamma*H;
y(5) = gamma*H;

end