%rolldie test
clear;
%make a matrix with random numbers of specific range
S1 = randi([1 6],5,2)

%randi([range], rows, columns)


% Add the first column to the second column
Sum2 = S1(:,1) + S1(:,2)

% : means all. So all rows but only 1 column
sum3 = S1(:, 1) + S1(:, 2)

Sevens = Sum2 == 7
fours = sum3 == 4

T4 = sum(fours)

Prop4 = T4/5

%print
fprintf("The proportion of 4's is %f\n", Prop4);


