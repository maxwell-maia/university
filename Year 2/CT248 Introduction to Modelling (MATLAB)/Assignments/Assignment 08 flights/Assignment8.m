clear;

flights = readtable("Flights.csv");

%Clean missing values
flights_c = flights(~isnan(flights.Day), :);

%Check
size(flights_c)

%Change cell values to string for origin and dest
flights_c.origin = string(flights_c.origin);
flights_c.dest = string(flights_c.dest);

%Check number of missing values for dep_delay
summary(flights_c)

%Filter missing values of departure delay
flights_clean = flights_c(~isnan(flights_c.dep_delay), :)

%Difference in records between tables
height(flights_c) - height(flights_clean)

%Filter dep_delay 120 mins
flights_final = flights_clean((flights_clean.dep_delay <= 120),:)


%Average delay per month
%Group by month
[G, month] = findgroups(flights_final.Month);

%Average
AvrDelayMonth = splitapply(@mean, flights_final.dep_delay, G);

%Table
res1 = table(month, AvrDelayMonth)

%Plot
plot(res1.month, res1.AvrDelayMonth, '-o');
title("Average Delay By Month");


%Average delay per hour
%Group by hour
[G, hour] = findgroups(flights_final.hour);

%Average per hour
AvrDelayHour = splitapply(@mean, flights_final.dep_delay, G);

%Table
res2 = table (hour, AvrDelayHour)

%Graph
plot(res2.hour, res2.AvrDelayHour, '-o');
title("Average Delay By Hour of the Day");


%Average Delay by Month and Origin
%Group by origin, Month
[G, origin, Month] = findgroups(flights_final.origin, flights_final.Month);

%Average Delay
AvrDelayMonthOrigin = splitapply(@mean, flights_final.dep_delay, G);

%Table
res3 = table(Month, origin, AvrDelayMonthOrigin)

%Plot graphs
%I don't know how
%plot(res3.Month, res3.origin, res3.AvrDelayMonthOrigin);