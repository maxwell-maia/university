clear;

%Read table
f = readtable("Flights.csv");

%Clean the table of missing values
f_c = f(~isnan(f.Day), :);

size(f_c)

%Convert cell values to string for origin and dest
f_c.origin = string(f_c.origin);
f_c.dest = string(f_c.dest);

%Check the number of missing values for dep_delay
summary(f_c)
dep_delay_missing_count = sum(isnan(f_c.dep_delay));
dep_delay_missing_count

%Filter out missing values of departure delay
flights_clean = f_c(~isnan(f_c.dep_delay), :);
flights_clean

%Check the difference in records between the two tables
height(f_c) - height(flights_clean)

%Filter out dep_delay greater than 120
flights_final = flights_clean((flights_clean.dep_delay <= 120),:);
flights_final

%Generate table and graph showing average delay per month
%group by month
%get groups
[G, month] = findgroups(flights_final.Month);
%average
AvrDelayMonth = splitapply(@mean, flights_final.dep_delay, G);
%table
res1 = table(month, AvrDelayMonth)
%plot
figure(1)
plot(res1.month, res1.AvrDelayMonth, '-o');
title("Average Delay By Month");

%Generate table and graph showing average delay per hour
%group by hour
%get groups
[G, hour] = findgroups(flights_final.hour);
%Average per hour
AvrDelayHour = splitapply(@mean, flights_final.dep_delay, G);
%Table
res2 = table (hour, AvrDelayHour)
%Graph
figure(2)
plot(res2.hour, res2.AvrDelayHour, '-o');
title("Average Delay By Hour of the Day");

%Generate table and graphs showing Average Delay by Month and Origin
%group by origin then Month
%find groups
[G, origin, Month] = findgroups(flights_final.origin, flights_final.Month);
%Average Delay
AvrDelayMonthOrigin = splitapply(@mean, flights_final.dep_delay, G);
%Table
res3 = table(Month, origin, AvrDelayMonthOrigin)
%Graph
%Count the number of different origin's
origin_unique = unique(origin)
%Get the count of the number of origins. This will inform the amount of
%graphs to plot
[origin_count, ~] = size(origin_unique)
%Plot graphs
for i = 1:origin_count
    figure(2+i)
    plot(Month(origin == origin_unique(i)),AvrDelayMonthOrigin(origin == origin_unique(i)), '-o');
    title(origin_unique(i));
end