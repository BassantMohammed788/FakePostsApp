package com.example.fakepostsapp.presentation.mapper

import com.example.fakepostsapp.domain.entity.PostEntityDomain
import com.example.fakepostsapp.presentation.model.PostEntityUi

fun PostEntityDomain.postDomainToUi(): PostEntityUi {
    return PostEntityUi(body=body,id=id,title=title,userId=userId)
}