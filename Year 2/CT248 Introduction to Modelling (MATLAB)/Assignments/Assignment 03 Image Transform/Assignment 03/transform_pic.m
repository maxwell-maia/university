function output = transform_pic(img)
    %Invert grayscale image
    output = 255 - img(:, :);
end