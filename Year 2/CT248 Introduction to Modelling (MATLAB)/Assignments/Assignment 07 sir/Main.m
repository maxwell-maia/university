clear;

contacts = linspace(3, 8, 20);
infectivity = 0.125;
alpha = 0.25;
beta = 0.02;
gamma = 0.10;

time_vec = 0:.25:100;
init_vec = [9999 1 0 0 0];

out_in_hospital = zeros(length(time_vec),length(contacts));
out_infected = zeros(length(time_vec),length(contacts));

for i = 1:length(contacts)
    [t,y] = ode45(@SIR, time_vec, init_vec, odeset, contacts(i), infectivity, alpha, beta, gamma);
    out_infected(:,i) = y(:,2);
    out_in_hospital(:,i) = y(:,4);
end

subplot(3, 1, 1), plot(t,out_infected), title("Infected Stock");
subplot(3, 1, 2), plot(t,out_in_hospital), title("People in Hospital");
subplot(3, 1, 3), plot(contacts, max(out_in_hospital), 'o'), title("Contacts v Peak in Hospital");
