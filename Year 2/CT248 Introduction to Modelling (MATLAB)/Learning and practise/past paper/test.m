clear;

M = [6 2 9 10 5; 3 7 3 9 10; 5 9 2 2 9; 9 2 2 9 4; 1 6 3 3 2]

lessfive = sum(sum(M<5))

M(M==10) = 0

%Can do a lot with only few lines of code. No need for a loop because
%matlab will generate a new array based on a condition (logical vector)
%and calculations can be done with that logical array with minimal effort.
%To for example, count values in an array or matrix, change certain values
%or filter arrays or matrixes with minimal code.

clear;

A = [6 9 7; 3 1 9; 5 2 2]
B = [2 0 0; 0 2 0; 0 0 2]

A*B

10*A*B

A + B

A.^B

clear;

%M = M*1.2

%p1 = X > 100

clear;

M = [1 0 ; 2 0]

sum(M)

clear;

f = @(x,y)[x.*y; x-y];
f(1:2, 3:4)


clear;

beta = 4.0;
gamma = 1;

[t, y] = ode45(@model, [0 50], [9999 1 0], odeset, beta, gamma);
plot(t,y);

clear;



% beta = linspace(1, 4, 5);
% gamma = linspace(0.2,1,5);
% time_vec = [0 50];
% y_out = zeros(length(time_vec), length(beta)*length(gamma));
% counter = 1;
% time = [];
% for i = 1:beta
%     for j = 1:gamma
%         [t,y] = ode45(@model, time_vec, [9999 1 0], odeset, beta(i), gamma(j));
%         y_out(:, counter) = y(:, 2);
%         counter = counter + 1;
%         time = t;
%     end
% end
% 
% plot(time, y_out);

clear;

beta = linspace(1,4,30);
gamma = linspace(1/5,1,30);
for i = beta
    for j = gamma
        [t,y] = ode45(@model, [0 50],[9999 1 0], odeset, i, j);
        plot(t,y(:,2), '-o');
        hold on;
    end
end

clear;

m = [1,2,3]
sum(m)

clear;



m = 1:5

f = @(m_in)[m_in((rem(m,2)==1))]

odd = f(m)















