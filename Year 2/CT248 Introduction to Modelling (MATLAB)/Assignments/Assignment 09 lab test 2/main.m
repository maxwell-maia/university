clear;

%Model
%dP/dt = r*P*( 1-(P/K) )
model = @(t, x, r, K)[r*x(1)*( 1-(x(1)/K) )];

%Variables
initial_population = 1000;
r = 0.15;
time_vec = 1:100;

%For Testing
%k_vec = linspace(1000, 1000000, 5);
k_vec = linspace(1000, 1000000, 50);

%Initialise matrix to store the result
results = zeros(length(time_vec), length(k_vec));

for i = 1:length(k_vec)
    %Run the model
    [t, y] = ode45(model, time_vec, initial_population, odeset, r, k_vec(i));
    results(:, i) = y;

    %Visualise results in a plot
    plot(t, y);
    hold on
end

