function d = roll_2_dice(N, seed)
    % Maxwell Maia
    % 21236277
    rng(seed);
    S1 = randi([1 6], N, 2);
    d = S1(:,1)+S1(:,2);
    d=d';
end