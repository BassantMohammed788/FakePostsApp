package com.example.fakepostsapp.presntation.mapper

import com.example.fakepostsapp.domain.entity.PostEntityDomain
import com.example.fakepostsapp.presntation.model.PostEntityUi

fun PostEntityDomain.postDomainToUi(): PostEntityUi {
    return PostEntityUi(body=body,id=id,title=title,userId=userId)
}