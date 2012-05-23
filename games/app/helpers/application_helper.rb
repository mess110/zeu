module ApplicationHelper
  def menu_item name, path
    klass = current_page?(path) ? " class='active'" : ""
    "<li#{klass}><a href='#{path}'>#{name}</a></li>".html_safe
  end

  def game_url game
    "/" + game[1]['type'] + "/" + game[0]
  end
end
