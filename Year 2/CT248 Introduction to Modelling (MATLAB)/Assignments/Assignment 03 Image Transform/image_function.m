function [pic2grayscale, transform_pic, transform_threshold] = image_function()
    pic2grayscale = @pic2grayscale;
    transform_pic = @transform_pic;
    transform_threshold = @transform_threshold;
end

function grayscaleImage = pic2grayscale(imageToGrayscale)
    R = imageToGrayscale(:, :, 1);
    G = imageToGrayscale(:, :, 2);
    B = imageToGrayscale(:, :, 3);

    grayscaleImage = 0.2989 * R + 0.5870 * G + 0.1140 * B;

    %grayscaleImage = rgb2gray(imageToGrayscale);
end

function imgOut = transform_pic(img)
    imgOut = 255 - img(:, :);
end

function imgOut = transform_threshold(img, threshold)
    imgOut = (img > threshold);
end