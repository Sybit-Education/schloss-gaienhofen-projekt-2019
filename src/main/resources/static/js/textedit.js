 $(document).ready(function () {
    $(".summernote").summernote({
      height: 300, // set editor height
      minHeight: 200, // set minimum height of editor
      // maxHeight: null, // set maximum height of editor
      focus: false,
      lang: 'de-DE',
      toolbar: [
        ['style', ['bold', 'italic', 'underline', 'clear']],
        ['para', ['ul', 'ol']],
        ['insert', ['link']],
        ['misc', ['undo', 'redo']]
      ],
      hint: {
        match: /:([\-+\w]+)$/,
        search: function (keyword, callback) {
          callback($.grep(emojis, function (item) {
            return item.indexOf(keyword) === 0;
          }));
        },
        template: function (item) {
          var content = emojiUrls[item];
          return '<img src="' + content + '" width="20" /> :' + item + ':';
        },
        content: function (item) {
          var url = emojiUrls[item];
          if (url) {
            return $('<img />').attr('src', url).css('width', 20)[0];
          }
          return '';
        }
      },
      callbacks: {
        onInit: function () {
          $('body > .note-popover').hide();
        }
      }
    });

    $.ajax({
      url: 'https://api.github.com/emojis',
      async: false
    }).then(function (data) {
      window.emojis = Object.keys(data);
      window.emojiUrls = data;
    });
  });
  
