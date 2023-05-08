clear;

A = [1 6 6 6 5 7 9 9 10];

uni = [];

for i = 1:length(A)
    % find tyhe number of matches
    num_matches = sum(A(i) == uni);
    
    fprintf("In loop A(i) = %f, n_m = %d\n", A(i), num_matches);

    %any time num matches is 0, add to vector
    if num_matches == 0
        uni = [uni A(i)];
    end
end