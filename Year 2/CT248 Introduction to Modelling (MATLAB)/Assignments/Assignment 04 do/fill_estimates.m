function grades = fill_estimates(grades)
    [r, c] = size(grades);
    for var = 1:c
        copy_col = grades(:, var);
        col_average = round( sum(copy_col(copy_col ~= 0)) / (r-sum(copy_col==0)));
        grades(grades(:,var)==0, var) = col_average;
    end
end