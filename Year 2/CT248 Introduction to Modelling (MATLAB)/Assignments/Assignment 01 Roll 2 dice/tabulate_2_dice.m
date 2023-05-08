function [freq, prop] = tabulate_2_dice(d)
    % Maxwell Maia
    % 21236277

    freq = zeros(1,12);
    prop = zeros(1,12);

    for i = 1:length(d)
        freq(d(i)) = freq(d(i))+1;
    end

    prop = freq./length(d);
    
end
