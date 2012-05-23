module ApplicationHelper
  def menu_item name, path
    klass = current_page?(path) ? " class='active'" : ""
    "<li#{klass}><a href='#{path}'>#{name}</a></li>".html_safe
  end

  def game_url game
    "/" + game[1]['type'] + "/" + game[0]
  end

  def logo server_info
    if server_info['ok'] == true
      image_tag 'green.png'
    else
      image_tag 'red.png'
    end
  end
end
